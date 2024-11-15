import { Request, Response, NextFunction } from "express";
import { ZodType } from "zod";

// The validate function uses function "currying" because we want to take the first function, that takes in a schema
// and returns a req, res, and next step and pass what is returned to the next function where we parse our schema.
const validate =
    (schema: ZodType) => (req: Request, res: Response, _: NextFunction) => {
        try {
            schema.parse({
                body: req.body,
                query: req.query,
                params: req.params,
            });
        } catch (e: any) {
            return res.status(400).send(e.errors);
        }
    };

export default validate;
