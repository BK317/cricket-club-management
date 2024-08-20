import React from 'react';
import { Container,  Row, Col, Card,  } from 'react-bootstrap';
import AdminLayout from '../components/AdminLayout';

const AdminDashboard = () => {
  return (
    <Container>
      <h1 className="my-4">Admin Dashboard</h1>
      
      {/* Statistics Summary with added margin between columns */}
      <Row className="mb-4">
        <Col md={3}>
          <Card className="mb-4">
            <Card.Body>
              <Card.Title>Total Teams</Card.Title>
              <Card.Text>20</Card.Text>
            </Card.Body>
          </Card>
        </Col>
        <Col md={3}>
          <Card className="mb-4">
            <Card.Body>
              <Card.Title>Total Players</Card.Title>
              <Card.Text>100</Card.Text>
            </Card.Body>
          </Card>
        </Col>
        <Col md={3}>
          <Card className="mb-4">
            <Card.Body>
              <Card.Title>Upcoming Matches</Card.Title>
              <Card.Text>5</Card.Text>
            </Card.Body>
          </Card>
        </Col>
        <Col md={3}>
          <Card className="mb-4">
            <Card.Body>
              <Card.Title>Recent Match Results</Card.Title>
              <Card.Text>3</Card.Text>
            </Card.Body>
          </Card>
        </Col>
      </Row>

      {/* Activity Feed */}
      <Card className="mb-4">
        <Card.Body>
          <Card.Title>Activity Feed</Card.Title>
          <ul>
            <li>Admin added a new team: Team A</li>
            <li>User updated player stats for Player X</li>
          </ul>
        </Card.Body>
      </Card>
</Container>
      

     
   
  );
}

export default AdminDashboard;
