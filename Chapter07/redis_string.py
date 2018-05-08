# -*- coding: utf-8 -*-

import redis

pool = redis.ConnectionPool(host='127.0.0.1', port=6379)
r = redis.Redis(connection_pool=pool)

#1 
print('\n#1')
r.set('name', 'xinping', ex=30)
print( r.get('name'))

#2
print('\n#2')
r.setnx('name2', 'xinping' )
print( r.get('name2'))

#3
print('\n#3')
r.setex('name3', 'xinping' , 30 )
print( r.get('name3'))

#4
print('\n#4')
r.psetex('name4', 10000, 'xinping'  )
print( r.get('name4'))

#5 
print('\n#5')
r.mset( name5='wangwu', name6='lisi'  )
r.mset({'name7': 'zhangsan' })

#6 
print('\n#6')
print( r.mget("name5","name6","name7")) 
print( r.mget(["name5","name7","name7"])) 

#7 
print('\n#7')
r.set('name', 'xinping') 
print(r.getset('name', 'lisi'))
print(r.get('name'))

#8
print('\n#8')
r.set('email','xpws2006@163.com')
print(r.getrange('email',0,7) )     #显示为字节类型

r.set('name','测试')
nameBytes = r.getrange('name',0,5)
print (bytes.decode(nameBytes )  ) 




