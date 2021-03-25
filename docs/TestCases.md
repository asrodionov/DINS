| Summary  | Steps  | Expected result  |
|---|---|---|---|---|
| Create resourse by correct fields | Execute HTTP request :
POST https://jsonplaceholder.typicode.com/posts/
Body request:
[
  {
    “UserId”:1, 
    "title": "title new post",
    "body": "body new post"
  }
]
  | 1.Server response 201 (Created)
    2. New post does exist in the list of posts: https://jsonplaceholder.typicode.com/posts/ 
  |   

|   |   |   |   
|   |   |   |   
