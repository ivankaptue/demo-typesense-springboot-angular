import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AppService {

  constructor(private http: HttpClient) {
  }

  getHelloWorld(): Observable<string> {
    const url = `http://backend:8080`;
    return this.http.get<string>(url);
  }

}
