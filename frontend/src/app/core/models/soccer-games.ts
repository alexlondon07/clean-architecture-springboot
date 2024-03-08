import { Player } from "./player";

export class SoccerGames {
  id: number;
  date: Date;
  time: string;
  field: string;
  fieldNumber: string;
  price: number;
  playerNumber: number;
  players: Player[];
  description: string;
  status: string;
  enable: boolean;
  created_at: Date;
  updated_at: Date;
}
