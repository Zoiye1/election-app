export interface Party {
  id: number
  registeredName: string;
}

export interface ConstituencyVotes {
  id: number
  constituencyName: string;
  votes: number;
}

export interface PartyConstituencyResult {
  id: number
  party: Party;
  constituencyVotes: ConstituencyVotes;
}

export interface Election {
  id: string;
  totalCounted?: number;
  partyResults?: Party[];
  partyConstituencyResults: PartyConstituencyResult[];
}
