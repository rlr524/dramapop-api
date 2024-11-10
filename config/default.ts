import dotenv from "dotenv";

dotenv.config();

export default {
    port: 1337,
    version: "v1",
    environment: "dev",
    saltWorkFactor: 10,
    dbUri: `mongodb+srv://${process.env.DB_USER}:${process.env.DB_PASSWORD}@cluster0.${process.env.DB_ID}.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0`,
}