FROM node:16-alpine AS builder

WORKDIR /app

COPY package.json ./

RUN npm install --force 
RUN npm run --configuration=production

COPY . .

RUN npm run build

FROM nginx:1.23

COPY ./nginx/nginx.config /etc/nginx/nginx.config

WORKDIR /app

COPY --from=builder /app/dist /usr/share/nginx/html

EXPOSE 80

CMD ["nginx", "-g", "daemon off;"]