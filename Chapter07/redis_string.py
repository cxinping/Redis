# -*- coding: utf-8 -*-

import redis

pool = redis.ConnectionPool(host='127.0.0.1', port=6379)
r = redis.Redis(connection_pool=pool)

r.set('name', 'xinping', ex=30)
print( r.get('name'))


r.setnx('name2', 'xinping' )
print( r.get('name2'))

r.setex('name3', 'xinping' , 30 )
print( r.get('name3'))

r.psetex('name4', 10000, 'xinping'  )
print( r.get('name4'))

