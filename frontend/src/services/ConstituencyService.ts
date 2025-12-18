import { CustomLogger } from "@/utils/CustomLogger";
import type { ConstituencyPartyVotes, TopConstituencies } from "@/interfaces/IElectionData";
const API_BASE_URL = import.meta.env.VITE_API_BASE_URL;

/**
 * Service for fetching constituency and election data
 */
export class ConstituencyService {

  public static logger = new CustomLogger();

  /**
   * Generic fetch helper that handles 404 gracefully
   */
  private static async fetchJson<T>(url: string): Promise<T | null> {
    try {
      const response = await fetch(url, {
        headers: { Accept: "application/json" }
      });

      if (response.status === 404) {
        // 👉 Specifiek deze endpoint
        if (url.includes("/constituency-votes-percentage")) {
          const partyName = new URL(url).searchParams.get("partyName");
          throw new Error(`${partyName} doesn't exist in this election`)
        }
      }

      if (!response.ok) {
        throw new Error(`${response.status}`);
      }

      return await response.json();
    } catch (error) {
      this.logger.error(`Fetch failed for URL: ${url}`, error);
      throw error;
    }
  }


  public static async getConstituencyData(electionId: string, constituencyName: string): Promise<ConstituencyPartyVotes[] | null> {
    const url = `${API_BASE_URL}/elections/${electionId}/${constituencyName}`;
    return this.fetchJson<ConstituencyPartyVotes[]>(url);
  }

  public static async getVotesPercentage(electionId: string, constituencyName: string): Promise<number | null> {
    const url = `${API_BASE_URL}/elections/${electionId}/${constituencyName}/votes-percentage`;
    return this.fetchJson<number>(url);
  }

  public static async getConstituencyVotesPercentage(
    electionId: string,
    constituencyName: string,
    partyName: string | null
  ): Promise<number | null> {
    if (!partyName) {
      return null;
    }
    const encodedParty = encodeURIComponent(partyName);
    const url = `${API_BASE_URL}/elections/${electionId}/${constituencyName}/constituency-votes-percentage?partyName=${encodedParty}`;
    return this.fetchJson<number>(url);
  }

  public static async getPartyGrowth(previousElectionId: string, currentElectionId: string, constituencyName: string, partyName: string | null): Promise<number | null> {
    if (!partyName) {
      return null;
    }

    const encodedParty = encodeURIComponent(partyName);
    const url = `${API_BASE_URL}/elections/${previousElectionId}/${currentElectionId}/${constituencyName}/party-growth?partyName=${encodedParty}`;
    return this.fetchJson<number>(url);
  }

  public static async getTopPerformingConstituencyByParty(
    electionId: string,
    partyName: string | null
  ): Promise<TopConstituencies[] | null> {
    if (!partyName) {
      return null;
    }

    const encodedParty = encodeURIComponent(partyName);
    const url = `${API_BASE_URL}/elections/${electionId}/top-constituencies?partyName=${encodedParty}`;
    return this.fetchJson<TopConstituencies[]>(url);
  }

  public static async getTotalConstituencyVote(electionId: string, constituencyName: string): Promise<number | null> {
    const url = `${API_BASE_URL}/elections/${electionId}/${constituencyName}/totalVotes`;
    return this.fetchJson<number>(url);
  }
}
