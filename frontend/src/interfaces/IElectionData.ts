export interface Party {
  id: number
  registeredName: string;
}

export interface Constituency{
  id: number;
  name: string;
}

export interface ConstituencyPartyVotes {
  id: number
  party: Party;
  constituency: Constituency;
  votes: number
}

export interface Election {
  id: string;
  totalCounted?: number;
  partyResults?: Party[];
  constituencyPartyVotes: ConstituencyPartyVotes[];
}
