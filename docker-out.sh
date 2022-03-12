cp -r coach-soul-gateway/src/docker dockerout/coach-soul-gateway
cp -r coach-server/coach-auth-server/src/docker dockerout/coach-auth-server
cp -r coach-server/coach-business-server/src/docker dockerout/coach-business-server
cp -r coach-server/coach-order-server/src/docker dockerout/coach-order-server
cp -r coach-server/coach-user-server/src/docker dockerout/coach-user-server
cp docker-compose-server.yml dockerout/docker-compose.yml

scp -r /Users/evol/source/github/spring-coach/dockerout root@10.211.55.11:/home/admin/coach-service