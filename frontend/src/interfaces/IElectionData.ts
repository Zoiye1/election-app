export interface Party {
  id: number
  partyName: string;
}

export interface Constituency{
  id: number;
  name: string;
}

export interface ConstituencyPartyVotes {
  id: number
  partyName: string;
  constituency: Constituency;
  votes: number
}

export interface Election {
  id: string;
  totalCounted?: number;
  partyResults?: Party[];
  constituencyPartyVotes: ConstituencyPartyVotes[];
}
