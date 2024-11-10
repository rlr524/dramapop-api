import dotenv from "dotenv";

dotenv.config();

export default {
    port: 1337,
    dbUri: `mongodb+srv://${process.env.DB_USER}:${process.env.DB_PASSWORD}@cluster0.ujyxu.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0`,
}