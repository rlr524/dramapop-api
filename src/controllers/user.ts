import { Request, Response } from "express";
import log from "../utils/logger";
import { createUser } from "../services/user";
import { CreateUserInput } from "../schema/user";

export async function createUserHandler(
    req: Request<{}, {}, CreateUserInput["body"]>,
    res: Response
) {
    try {
        const user = await createUser(req.body);
        return user;
    } catch (e: any) {
        log.error(e);
        // Throw a 409 Conflict status as a throw should only happen if the unique
        // constraint on user email is violated
        return res.status(409).send(e.message);
    }
}
