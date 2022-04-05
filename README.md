news-controller
REST API написанное с помощью kotlin и либы ktor

Тех.задание:
http://testtask.sebbia.com/swagger-ui.html/

//Возвращает список категорий  
Get localhost:8080/news-controller/categories  
  
//Возвращает список новостей в выбранной категории  
Get localhost:8080/news-controller/{id}/news(?page={page})  
  
//Возвращает новость с подробным описанием  
Get localhost:8080/news-controller/details(?id={id})  
