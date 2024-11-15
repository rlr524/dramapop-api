import { Express, Request, Response } from "express";
import { createUserHandler } from "./controllers/user";
import config from "config";
import validateResource from "./middleware/validateResource";
import { createUserSchema } from "./schema/user";

let version = config.get("version");

function routes(app: Express) {
    app.get(`/api/${version}/healthcheck`, (_: Request, res: Response) =>
        res.sendStatus(200)
    );

    app.post(
        `/api/${version}/users`,
        validateResource(createUserSchema),
        createUserHandler
    );
}

export default routes;
