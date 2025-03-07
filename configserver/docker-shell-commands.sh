docker build --platform=linux/amd64 . -t lasalhettiarachchi/configserver:s3
# provides all the images in the system
docker images
docker run -p 8080:8080 lasalhettiarachchi/configserver:s3
# aab is the image id, provides the details of the image by invoking docker image
docker inspect image aab
docker push docker.io/lasalhettiarachchi/configserver:s3
