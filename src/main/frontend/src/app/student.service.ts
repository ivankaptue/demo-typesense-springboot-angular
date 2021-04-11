import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {StudentModel} from './student.model';
import {Observable} from 'rxjs';
import {PageModel} from './page.model';

@Injectable({
  providedIn: 'root'
})
export class StudentService {

  private readonly baseUrl = 'http://192.168.8.102:8080/students';

  constructor(private http: HttpClient) {
  }

  public create(student: StudentModel): Observable<StudentModel> {
    return this.http.post<StudentModel>(this.baseUrl, student);
  }

  public update(id: number, student: StudentModel): Observable<StudentModel> {
    const url = `${this.baseUrl}/${id}`;
    return this.http.put<StudentModel>(url, student);
  }

  public delete(id: number): Observable<void> {
    const url = `${this.baseUrl}/${id}`;
    return this.http.delete<void>(url);
  }

  public findById(id: number): Observable<StudentModel> {
    const url = `${this.baseUrl}/${id}`;
    return this.http.get<StudentModel>(url);
  }

  public findAll(page: number = 1, size: number = 10): Observable<PageModel<StudentModel>> {
    const params = new HttpParams().set('page', page.toString()).set('size', size.toString());
    return this.http.get<PageModel<StudentModel>>(this.baseUrl, {params});
  }

  public search(query: string = '*', page: number = 1, size: number = 10): Observable<PageModel<StudentModel>> {
    const url = `${this.baseUrl}/search`;
    const params = (new HttpParams())
      .set('q', query)
      .set('page', page.toString())
      .set('size', size.toString());
    return this.http.get<PageModel<StudentModel>>(url, {params});
  }

}
