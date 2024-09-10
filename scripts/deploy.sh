echo @@@ stop docker containers
sudo docker-compose -f ./deskmood/docker/docker-compose."$2".yml down

echo @@@ remove app image
sudo docker rmi wwan13/deskmood-"$1":"$2"

echo @@@ start docker comtainers
sudo docker-compose -f ./deskmood/docker/docker-compose."$2".yml up -d