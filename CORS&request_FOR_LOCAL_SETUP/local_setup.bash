# !/bin/bash

cp -f 'CORS&request_FOR_LOCAL_SETUP/request.js' 'frontend/src/utils/request.js'
cp -f 'CORS&request_FOR_LOCAL_SETUP/CORSConfig.java' 'backend/src/main/java/com/mingzhe/backend/config/CORSConfig.java'
cp -f 'CORS&request_FOR_LOCAL_SETUP/backend_setup.bash' 'backend/backend_setup.bash'