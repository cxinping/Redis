# -*- coding: utf-8 -*-
import redis
pool = redis.ConnectionPool(host='127.0.0.1', port=6379)
r = redis.Redis(connection_pool=pool)

#1
print('\n#1')
r.lpush('nums' , 1,2,3)

#2
print('\n#2')
r.delete('nums')
r.lpush('nums' , 1,2,3)
r.linsert('nums' , where= 'before', refvalue='2' , value='a')

#3
print('\n#3')
r.delete('nums')
r.lpush('nums' , 1,2,3)
r.lset('nums', 2, 'b')

#4
print('\n#4')
r.delete('nums')
r.lpush('nums' , 1,2,3)
#r.lrem('nums', 3, 1)
