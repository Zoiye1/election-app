import type { Candidate } from "@/interfaces/Candidate.ts";

const API_BASE_URL = 'http://localhost:8080/api';


/**
 * Fetches all candidates from the backend
 * @returns {Promise<Candidate[]>} List of all candidates
 */
export async function getAllCandidates (): Promise<Candidate[]>{
  const response = await fetch(`${API_BASE_URL}/candidates`);

  if (!response.ok) {
    throw new Error(`HTTP error! status: ${response.status}`);
  }

  const data: Candidate[] = await response.json();
  return data;
}
