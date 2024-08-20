// src/pages/PlayersPage.js
import {React,useState,useEffect} from 'react';
import { Container, Table, Button } from 'react-bootstrap';
import { getPlayers } from '../services/playerService';

function PlayersPage() {

  const [players, setPlayers] = useState([]);
  const [error, setError] = useState(null)

  
  useEffect(() => {
    const fetchPlayers = async () => {
      try {
        const fetchedPlayers = await getPlayers();
        setPlayers(fetchedPlayers);
      } catch (error) {
        setError('Error fetching players');
       
      }
    };

    fetchPlayers();
  }, []);
  // Filter players with approved account status
  const approvedPlayers = players.filter(player => player.accountStatus === "APPROVED");

  return (
    <Container className="my-4">
      <h1 className="text-center mb-4">Players</h1>
      <Table striped bordered hover>
        <thead>
          <tr>
            <th>Name</th>
            <th>Position</th>
            <th>Batting Style</th>
            <th>Bowling Style</th>
            {/* <th>Action</th> */}
          </tr>
        </thead>
        <tbody>
          {approvedPlayers.length > 0 ? (
            approvedPlayers.map(player => (
              <tr key={player.id}>
                <td>{player.name || 'N/A'}</td>
                <td>{player.position}</td>
                <td>{player.battingStyle}</td>
                <td>{player.bowlingStyle}</td>
                {/* <td>
                  <Button variant="info" size="sm">View Profile</Button>
                </td> */}
              </tr>
            ))
          ) : (
            <tr>
              <td colSpan="5" className="text-center">No approved players found {error}</td>
            </tr>
          )}
        </tbody>
      </Table>
    </Container>
  );
}

export default PlayersPage;
