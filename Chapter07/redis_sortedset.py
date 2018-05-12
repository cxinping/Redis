# -*- coding: utf-8 -*-

import redis
pool = redis.ConnectionPool(host='127.0.0.1', port=6379)
r = redis.Redis(connection_pool=pool)

#1
print('\n#1')
r.zadd('zset1',num1=1,num2=2,num3=3)

#
print(r.zrange('s4',0,-1))



























