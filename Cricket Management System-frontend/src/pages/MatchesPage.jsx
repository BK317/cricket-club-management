// src/pages/MatchesPage.js
import React, { useEffect, useState } from 'react';
import { Container, Card, Button, Row, Col } from 'react-bootstrap';
import { getMatches } from '../services/matchService'; // Adjust the import path as needed

function MatchesPage() {
  const [matches, setMatches] = useState([]);

  useEffect(() => {
    const fetchMatches = async () => {
      try {
        const data = await getMatches();
        setMatches(data);
      } catch (error) {
        console.error('Error fetching matches', error);
      }
    };

    fetchMatches();
  }, []);

  return (
    <Container className="my-4">
      <h1 className="text-center mb-4">Upcoming Matches</h1>
      <Row>
        {matches.map(match => (
          <Col md={4} key={match.id} className="mb-4">
            <Card>
              <Card.Body>
                <Card.Title>Match Details</Card.Title>
                <Card.Text>
                  Date: {match.date}<br />
                  Team A Score: {match.teamAScore}<br />
                  Team B Score: {match.teamBScore || 'TBA'}<br />
                  Ground ID: {match.groundId || 'TBA'}<br />
                  Tournament ID: {match.tournamentId || 'TBA'}<br />
                  Team A ID: {match.teamAId || 'TBA'}<br />
                  Team B ID: {match.teamBId || 'TBA'}
                </Card.Text>
                {/* <Button variant="primary">Match Details</Button> */}
              </Card.Body>
            </Card>
          </Col>
        ))}
      </Row>
    </Container>
  );
}

export default MatchesPage;
