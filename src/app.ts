import express from "express";
import config from "config";
import connect from "./utils/connect";
import log from "./utils/logger";
import routes from "./routes";

const port = config.get<number>("port");
const uri = config.get<string>("dbUri");

const app = express();

app.listen(port, async () => {
    log.info(`App is running on port ${port}`);
    
    await connect();
    
    routes(app)
})
