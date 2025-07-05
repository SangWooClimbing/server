docker buildx build --platform linux/amd64,linux/arm64 -t moonjin974/climbing: -f Dockerfile . --push
docker system prune -a -f
