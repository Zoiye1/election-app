
# Start with Node.js to build the app
FROM node:20 AS build
LABEL author="Mats"

# Set working directory
WORKDIR /app

# Copy package files first for better caching
COPY package*.json ./
RUN npm install

# Copy source code and build
COPY . .
RUN npm run build-only


# Use nginx to serve the built app
FROM nginx:alpine

# Copy build output to nginx
COPY --from=build /app/dist /usr/share/nginx/html

# Document exposed port
EXPOSE 80

# Start nginx in foreground
CMD ["nginx", "-g", "daemon off;"]