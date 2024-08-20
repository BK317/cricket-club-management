// src/pages/ProfilePage.js
import React from 'react';
import { Container, Row, Col, Card, ListGroup, Button } from 'react-bootstrap';

function ProfilePage({user}) {
  // Mock data - replace with actual user data
 

  return (
    <Container className="my-4">
      <h1 className="text-center mb-4">User Profile</h1>
      <Row>
        <Col md={6} className="mx-auto">
          <Card>
            <Card.Body>
              <Card.Title>{user.name}</Card.Title>
              <Card.Text>
                 Username: {user.username}
             </Card.Text>
            </Card.Body>
          </Card>
        </Col>
      </Row>
    </Container>
  );
}

export default ProfilePage;