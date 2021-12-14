# download a base image
FROM node:lts-alpine3.14

# make the 'app' folder the current working directory
WORKDIR /reactapp

# copy both 'package.json' and 'package-lock.json' (if available)
COPY package*.json ./

# install project dependencies
RUN npm install

# copy project files and folders to the current working directory (i.e. 'app' folder)
COPY . .

# open the port on which the server will start
EXPOSE 3000
	
# run the front end server
CMD [ "npm", "start" ]
