export interface Party {
  partyName: string;
}

export interface ConstituencyVotes {
  constituencyName: string;
  votes: number;
}

export interface PartyConstituencyResult {
  party: Party;
  constituencyVotes: ConstituencyVotes;
}

export interface Election {
  Id: string;
  totalCounted: number;
  partyResults: Party[];
  partyConstituencyResults: PartyConstituencyResult[];
}
