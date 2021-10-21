import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Inventory } from '../models/inventory';

@Injectable({
  providedIn: 'root'
})
export class InventoryService {

  private url:string = "http://localhost:8090/"

  constructor(private http:HttpClient) { }

  getInventory(trainerId:number) {
    return this.http.get(this.url + "inventory/:trainerId") as Observable<Inventory>;
  }
}
