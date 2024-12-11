import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Configuration } from './configuration';
import { Observable, interval, switchMap } from 'rxjs';

@Injectable({
  providedIn: 'root'
})

export class ConfigurationService {

  private url1 = "http://localhost:8080/api/config-data" ;
  private url2 = "http://localhost:8080/api/outputs";
  private url3 = "http://localhost:8080/api/sould-tickets";

  constructor(private http: HttpClient ) {}

  // Method to get configuration data from the API
  public getConfiguration(): Observable<Configuration> {
    return this.http.get<Configuration>(this.url1);
  }

  // Method to get configuration data from the API
  public saveConfiguration(configData: Configuration): Observable<Configuration> {
    return this.http.post<Configuration>(this.url1, configData);
  }

  // Method to get configuration data from the API
  public getConsoleOutputs(): Observable<string[]> {
    return this.http.get<string[]>(this.url2);
  }

  //send request to the backend from request consoleOutputs array
  pollConsoleOutputs(intervalTime: number): Observable<string[]> {
    return interval(intervalTime).pipe(
      switchMap(() => this.getConsoleOutputs())
    );
  }

    // Method to get the number of sold tickets from the API
  public getSouldTickets(): Observable<number> {
    return this.http.get<number>(this.url3);
  }

  //send request to the backend from request sold ticket count
  pollSouldTickets(intervalTime: number): Observable<number> {
    return interval(intervalTime).pipe(
      switchMap(() => this.getSouldTickets())
    );
  }

}
