




ONLY from https://docs.traefik.io/v1.7/user-guide/kubernetes/
kubectl apply -f https://raw.githubusercontent.com/containous/traefik/v1.7/examples/k8s/traefik-rbac.yaml
kubectl apply -f https://raw.githubusercontent.com/containous/traefik/v1.7/examples/k8s/traefik-ds.yaml


#ingress sample 
kubectl apply -f https://raw.githubusercontent.com/containous/traefik/v1.7/examples/k8s/ui.yaml

FINISH



https://kubernetes.io/docs/concepts/services-networking/ingress/#ingress-controllers




https://docs.traefik.io/



#exemplo
https://kubernetes.io/docs/concepts/services-networking/ingress/#simple-fanout



# liberar master para rodar nodes
kubectl taint nodes --all node-role.kubernetes.io/master-



    http:
      paths:
      - backend:
          serviceName: traefik-dashboard
          servicePort: dashboard-http