# ===== 1) Base (deps) =====
FROM node:20-alpine AS deps
WORKDIR /app

# Kopiraj samo package* za boljši layer cache
COPY package*.json ./

# Namesti samo production odvisnosti
# (če imaš package-lock.json bo uporabljen npm ci)
RUN --mount=type=cache,target=/root/.npm \
    if [ -f package-lock.json ]; then npm ci --omit=dev; else npm i --omit=dev; fi

# ===== 2) Runner =====
FROM node:20-alpine AS runner
WORKDIR /app
ENV NODE_ENV=production

# Kopiraj node_modules iz prejšnje faze in preostalo kodo (vklj. WSDL)
COPY --from=deps /app/node_modules ./node_modules
COPY . ./

# Servis posluša na 9090
EXPOSE 9090

USER node

CMD ["node", "index.js"]
