# -*- coding: utf-8 -*-

import redis
pool = redis.ConnectionPool(host='127.0.0.1', port=6379)
r = redis.Redis(connection_pool=pool)

#1
print('\n#1')
r.sadd('sets', 1,2,3,4)

#2
print('\n#2')
r.delete('sets')
r.sadd('sets', 1,2,3,4)
print( r.scard('sets') )

#3
print('\n#3')
r.delete('sets')
r.sadd('sets', 1,2,3,4)
















