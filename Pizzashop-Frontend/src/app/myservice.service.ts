import { Injectable } from '@angular/core';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { Observable } from 'rxjs';
import { category } from './category';
import { product } from './product';
import { productbycategory } from './productbycategory';
import { users } from './users';

@Injectable({
  providedIn: 'root'
})
export class MyserviceService {

public static login?:boolean=false;
public static username?:string='';

  constructor(private http : HttpClient) { }



public createTransaction(amount:any)
{
return this.http.get("http://localhost:9092/api/getTransaction/"+amount);
}



checkuserexists(u:users):Observable<users>
{
  return this.http.post<users>(`http://localhost:9092/api/checkuser`,u);
}



  getallcategory():Observable<category[]>
  {
    return this.http.get<category[]>(`http://localhost:9092/api/allcat`)
  }


  getcatbyid():Observable<category>
  {
    return this.http.get<category>(`http://localhost:9092/api/catbyid/1`);
  }


  addCategory(c : category):Observable<category>
  {
    return this.http.post<category>(`http://localhost:9092/api/addcat`,c);
  }

  updatecategory(c:category):Observable<category>
  {
    return this.http.put<category>(`http://localhost:9092/api/updatecat/`+c.catid,c);
  }

  deletecategory(c:category):Observable<category>
  {
    return this.http.delete<category>(`http://localhost:9092/api/deletecat/`+c.catid);
  }



  getallproduct():Observable<product[]>
  {
    return this.http.get<product[]>(`http://localhost:9092/api/allprdt`)
  }


  getallproductbycategory(e:any):Observable<productbycategory[]>
  {
    return this.http.get<productbycategory[]>(`http://localhost:9092/api/allprdtcat/`+e)
  }

  
}

