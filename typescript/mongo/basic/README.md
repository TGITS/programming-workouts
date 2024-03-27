# Basic example node program using MongoDB Driver and TypeScript

## Running the containers with the MongoDB instance

```sh
docker compose -f dc-mongodb.yml up -d
```

To check that all is ok, you can open a shell on the container and connect to the MongoDB instance with the CLI by running `mongosh` as described thereafter :

* To be connected as administrator and be able to see available users and databases
  * `mongosh --port 27017  --authenticationDatabase "admin" -u "root" -p`
    * You will be asked the password (it is defined in the `.env` file in the variable `MONGO_INITDB_ROOT_PASSWORD`)
  * you can show the databases with `show dbs`
* To be connected as the user defined in the `mongo-init.js` file (`jon_snow`) :
  * `mongosh got_db -u "jon_snow" -p`
    * You has then to type the password specified for the user `jon_snow` in the `mongo-init.js` file (the password is `ygritte` by the way)
  * You can express a query against the database : `db.got_seasons_collection.find({}, { season : 1, year : 1 })`

If you want to stop the containers, you just have to execute in the command line the following command :

```sh
docker compose -f dc-mongodb.yml down
```

## Initialisation of the project

```sh
npm init
npm install typescript --save-dev
npx tsc --init
npm install --save-dev @types/node
npm install mongodb
npm install dotenv
```

## Bibliography

* [MongoDB with Node.js](https://www.mongodb.com/languages/mongodb-with-nodejs)
  * Ressources de l'article à regarder :
    * [MongoDB Node Driver](https://www.mongodb.com/docs/drivers/node/current/)
    * This [guide](https://www.mongodb.com/docs/drivers/node/current/quick-start/) shows you how to create an application that uses the MongoDB Node.js driver to connect to a MongoDB cluster hosted on MongoDB Atlas.
    * [MongoDB & Node.js: Connecting & CRUD Operations (Part 1 of 4)](https://www.youtube.com/watch?v=fbYExfeFsI0)
    * [MongoDB and Node.js Tutorial - CRUD Operations](https://www.mongodb.com/developer/languages/javascript/node-crud-tutorial/)
      * 17 min read • Published Feb 04, 2022 • Updated Aug 22, 2023
      * [Code source associée](https://github.com/mongodb-developer/nodejs-quickstart)
* [MongoDB Node.js Driver](https://mongodb.github.io/node-mongodb-native/)
  * > The next generation Node.js driver for MongoDB
* [Node.js Driver with TypeScript](https://www.mongodb.com/docs/drivers/node/current/fundamentals/typescript/)
* [Using Environment Variables in TypeScript with dotenv](https://medium.com/@sushantkadam15/using-environment-variables-in-typescript-with-dotenv-dc0c35939059)
* [A Step-by-Step Guide to Setting Up a Node.js Project with TypeScript](https://dev.to/pabath99/a-step-by-step-guide-to-setting-up-a-nodejs-project-with-typescript-4dn2)
* [How to use proper typescript types in mongodb find filter combined with a predefined schema?](https://stackoverflow.com/questions/70944602/how-to-use-proper-typescript-types-in-mongodb-find-filter-combined-with-a-predef)
* [MongoDB FindOptions when using Typescript](https://stackoverflow.com/questions/69506203/mongodb-findoptions-when-using-typescript)
  * [mongodb-typescript-example](https://github.com/mongodb-developer/mongodb-typescript-example)
