chmod +x runcrud.sh
./runcrud.sh
if  runcrud.sh; then
  echo "Success"
else
  echo "There were errors"
fi

open -a "Google Chrome" http://localhost:8080/crud/v1/task/getTasks
