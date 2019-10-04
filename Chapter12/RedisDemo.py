# -*- coding: utf-8 -*-

import redis

r = redis.Redis(host='localhost', port=6379, db=0)
r.set('password', 'abcdef')

print(r.get('password'))
