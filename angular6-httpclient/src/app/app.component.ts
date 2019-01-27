import { Component } from '@angular/core';
import {HttpClient} from '@angular/common/http'
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'app';
  name:string = '';
  public ipAddr:string = '';
  public longUrl:string = '';
  constructor(private httpClient:HttpClient) { }  
    onNameKeyUp(event:any){
      this.name = event.target.value; 
      
    }
   //this method sends the url and ip address of the client
    postProfile(){
      this.httpClient.get('http://www.geoplugin.net/json.gp')
        .subscribe(
          (data:any) =>{
            this.ipAddr = data.geoplugin_request;
            
          })
        

      this.httpClient.post('http://localhost:8080/generateUrl',{
        url : this.name,
        ipAddr : this.ipAddr  
      }).subscribe(
        (data:any) =>{
          this.longUrl = data.url;
          
        }
      )
    }

  }
