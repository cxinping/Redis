# -*- coding: utf-8 -*-

from rediscluster import RedisCluster

cluster_nodes = [{'host': '192.168.11.11', 'port': 8001},
                 {'host': '192.168.11.11', 'port': 8002},
                 {'host': '192.168.11.11', 'port': 8003},
                 {'host': '192.168.11.11', 'port': 8004},
                 {'host': '192.168.11.11', 'port': 8005},
                 {'host': '192.168.11.11', 'port': 8006}]

cluster = RedisCluster(startup_nodes=cluster_nodes)
cluster.set('book', 'pythoon')
print(cluster.get('book'))