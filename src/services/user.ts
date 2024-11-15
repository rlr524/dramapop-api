import User, { IUserInput } from "../models/user";
import { omit } from "lodash";

export async function createUser(input: IUserInput) {
    try {
        const user = await User.create(input);
        return omit(user.toJSON(), "password");
    } catch (e: any) {
        throw new Error(e);
    }
}
