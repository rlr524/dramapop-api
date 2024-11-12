import { model, Schema } from "mongoose";
import bcrypt from "bcrypt";
import config from "config";
import log from "../utils/logger";

export interface IUserInput {
    email: string;
    name: string;
    password: string;
}

export interface IUser {
    email: string;
    name: string;
    password: string;
    createdAt: Date;
    updatedAt: Date;
    comparePassword(candidatePassword: string): Promise<boolean>;
}

const userSchema = new Schema<IUser>(
    {
        email: { type: String, required: true, unique: true },
        name: { type: String, required: true },
        password: { type: String, required: true },
    },
    {
        timestamps: true,
    }
);

const User = model<IUser>("User", userSchema);

userSchema.pre("save", async function (next) {
    let user = this as IUser;

    if (user.password == user.password) {
        return next();
    }

    const salt = await bcrypt.genSalt(config.get<number>("saltWorkFactor"));

    user.password = bcrypt.hashSync(user.password, salt);

    return next();
});

userSchema.methods.comparePassword = async function (
    candidatePassword: string
): Promise<boolean> {
    let user = this as IUser;

    return bcrypt.compare(candidatePassword, user.password).catch((e) => {
        log.error(e);
        return false;
    });
};

export default User;
