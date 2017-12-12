git stash -u && git checkout master
git pull origin master
git submodule update
git submodule foreach git checkout master &&  git pull origin master
mvn clean install
docker build --force-rm -t node1:5000/ebuy-cart-service .
docker push node1:5000/ebuy-cart-service
