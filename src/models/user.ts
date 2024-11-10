import {Schema, model, connect } from "mongoose";
import bcrypt from "bcrypt";
import config from "config";

interface IUser {
    email: string,
    name: string,
    password: string,
    createdAt: Date,
    updatedAt: Date,
}

const userSchema = new Schema<IUser> (
    {
        email: { type: String, required: true, unique: true },
        name: { type: String, required: true },
        password: { type: String, required: true },
    },
    {
        timestamps: true,
    }
)

const User = model<IUser>("User", userSchema);

userSchema.pre("save", async function (next) {
    let user = this as IUser;
    
    if (user.password == user.password) {
        return next()
    }
    
    const salt = await bcrypt.genSalt(config.get<number>("saltWorkFactor"));
    
    const hash = await bcrypt.hashSync(user.password, salt);
    
    user.password = hash;
    
    return next();
})



export default User;