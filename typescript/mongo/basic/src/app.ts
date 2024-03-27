import dotenv from "dotenv";
import { MongoClient, Filter, FindOptions } from "mongodb";

dotenv.config(); // Load environment variables from .env file
const dbCconnectionURI = `${process.env.MONGO_DB_URL}:${process.env.MONGO_DB_PORT}`;
const username = process.env.MONGO_INITDB_ROOT_USERNAME; //process.env.MONGO_GOT_DB_USER;
const password = process.env.MONGO_INITDB_ROOT_PASSWORD; //process.env.MONGO_GOT_DB_PASSWORD;
const dbName = process.env.MONGO_GOT_DB || "";
const collectionName = process.env.MONGO_GOT_COLLECTION_SEASONS || "";
console.log("Connection URL:", dbCconnectionURI);
console.log("Username:", username);

async function findSeasonByNumber(
  client: MongoClient,
  dbName: string,
  dbCollection: string,
  seasonNumber: string
) {
    const result = await client
    .db(dbName)
    .collection(dbCollection)
    .findOne({season: seasonNumber});
  if (result) {
    console.log(
      `Found season ${seasonNumber} in the collection with the name '${dbCollection}' in database '${dbName}':`
    );
    console.log(result);
  } else {
    console.log(
      `No season ${seasonNumber} with the name '${dbCollection}' in database '${dbName}'`
    );
  }
};

async function main() {
  const uri = `mongodb://${username}:${password}@${dbCconnectionURI}`;

  const client = new MongoClient(uri);

  try {
    console.log("connection to mongodb");
    // Connect to the MongoDB cluster
    await client.connect();
    console.log("connected to mongodb");

    await findSeasonByNumber(client, dbName, collectionName, "1");
    await findSeasonByNumber(client, dbName, collectionName, "8");
    await findSeasonByNumber(client, dbName, collectionName, "9");
  } finally {
    // Close the connection to the MongoDB cluster
    await client.close();
    console.log("closing connection");
  }
}

main().catch(console.error);
