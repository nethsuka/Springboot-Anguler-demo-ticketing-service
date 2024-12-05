import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Configuration } from './configuration';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ConfigurationService {

  private url1 = "http://localhost:8080/api/config-data" ;

  constructor(private http: HttpClient ) {}

  public getConfiguration(): Observable<Configuration> {
    return this.http.get<Configuration>(this.url1);
  }

  public saveConfiguration(configData: Configuration): Observable<Configuration> {
    return this.http.post<Configuration>(this.url1, configData);
  }
}
