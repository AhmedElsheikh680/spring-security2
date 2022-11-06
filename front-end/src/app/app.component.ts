// @ts-ignore
import { Component } from '@angular/core';
import {PlayerService} from "./service/player.service";

// @ts-ignore
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'front-end';
  constructor(private playerService: PlayerService) {

  }
  ngOnInit(): void{
    this.playerService.getPlayer().subscribe(
      data =>{

      }
    )
  }
}
