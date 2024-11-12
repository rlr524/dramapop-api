import { Request, Response } from "express";
import log from "../utils/logger";

export function createUserHandler(req: Request, res: Response) {
    try {
        //const user = await // call create user service
    } catch (e: any) {
        log.error(e);
        // Throw a 409 Conflict status as a throw should only happen if the unique
        // constraint on user email is violated
        return res.status(409).send(e.message);
    }
}
