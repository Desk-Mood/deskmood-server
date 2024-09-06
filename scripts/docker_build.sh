echo "build applications start"

echo "[1] clean project"
./gradlew clean

echo "[2] build core api"
./gradlew deskmood-core:"$1":build

echo "[3] crete docker image"
docker build --platform linux/amd64 -t wwan13/deskmood-"$1":"$2" .

echo "[4] push to docker hub"
docker push wwan13/wwan13/deskmood-"$1":"$2"
