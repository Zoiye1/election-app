import type { ConstituencyPartyVotes } from "@/interfaces/IElectionData";
import type { Election } from "@/interfaces/IElectionData";

/**
 *
 * This service does a fetch to the endpoint /{electionId} to get
 * the constituency data results
 */
const API_BASE_URL = import.meta.env.VITE_API_BASE_URL;

export class ElectionService {

  public async getAllMunicipalityData(electionId: string): Promise<any[]> {
    const url = `${API_BASE_URL}elections/${electionId}/municipalities`;

    try {
      const response = await fetch(url, {
        method: "GET",
        headers: {"Accept": "application/json"  }
      });
      if (!response.ok) {
        throw new Error("request failed " + response.statusText);
      }
      return await response.json();
    } catch (error) {
      console.error('Fetch error:', error);
      throw error;
    }
  }

  public async getMunicipalityData(electionId: string, municipalityName: string): Promise<ConstituencyPartyVotes[]> {
    const url = `${API_BASE_URL}elections/${electionId}/municipalities/${municipalityName}`;

    try {
      const response = await fetch(url, {
        method: "GET",
        headers: {"Accept": "application/json"}
      });
      if (!response.ok) {
        throw new Error("request failed " + response.statusText);
      }
      return await response.json();
    } catch (error) {
      console.error('Fetch error:', error);
      throw error;
    }
  }

  public static async getTotalVotes(): Promise<number> {
    const url = `${API_BASE_URL}elections/TK2023/total-votes`;

    try {
      const response = await fetch(url, {
        // method: "GET",
        headers: {"Accept": "application/json"}
      });
      if (!response.ok) {
        throw new Error("request failed " + response.statusText);
      }
      return await response.json();
    } catch (error) {
      console.error('Fetch error:', error);
      throw error;
    }
  }

  public async getElection(electionId: string): Promise<Election> {
    const url = `${API_BASE_URL}/elections/${electionId}`;

    try {
      const response = await fetch(url, {
        method: "GET",
        headers: {"Accept": "application/json"}
      });
      if (!response.ok) {
        throw new Error(`request failed ${response.statusText}`);
      }
      return await response.json();
    } catch (error) {
      console.error('Fetch error:', error);
      throw error;
    }
  }

  public async getConstituencyData(electionId: string, constituencyName: string): Promise<ConstituencyPartyVotes[]> {
    const url = `${API_BASE_URL}/elections/${electionId}/${constituencyName}`;

    try {
      const response = await fetch(url, {
        method: "GET",
        headers: {"Accept": "application/json"}
      });
      if (!response.ok) {
        throw new Error("request failed " + response.statusText);
      }
      return await response.json();
    } catch (error) {
      console.error('Fetch error:', error);
      throw error;
    }
  }

  public static async getConstituencyVotesPercentage(electionId: string, constituencyName: string): Promise<number> {
    const url = `${API_BASE_URL}/elections/${electionId}/${constituencyName}/votes-percentage`;

    try {
      const response = await fetch(url, {
        method: "GET",
        headers: {"Accept": "application/json"}
      });
      if (!response.ok) {
        throw new Error(`request failed ${response.statusText}`);
      }
      return await response.json();
    } catch (error) {
      console.error('Fetch error:', error);
      throw error;
    }
  }
}
