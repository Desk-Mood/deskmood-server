echo "build applications start"

echo "[1] clean project"
./gradlew clean

echo "[2] build core api"
./gradlew deskmood-core:"$1":build

echo "[3] crete docker image"
cd deskmood-core/core-api
docker build --platform linux/amd64 -t wwan13/deskmood-"$1":"$2" .

echo "[4] push to docker hub"
docker push wwan13/deskmood-"$1":"$2"
