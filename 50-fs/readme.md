kubectl run py-fs --image=rogeriosilvarocha/py-fs:0.1 --port=8080 --replicas=2 deployment "echoserver" created



kubectl exec -it py-db-855466f6f9-bvggg  -- /bin/bash

/opt/mssql-tools/bin/sqlcmd -S localhost -U SA -P "Password12"