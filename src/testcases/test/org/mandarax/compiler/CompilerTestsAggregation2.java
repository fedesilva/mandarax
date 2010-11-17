/*
 * Copyright 2010 Jens Dietrich Licensed under the GNU AFFERO GENERAL PUBLIC LICENSE, Version 3
 * (the "License"); you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
 * http://www.gnu.org/licenses/agpl.html Unless required by applicable law
 * or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */

package test.org.mandarax.compiler;

import static org.junit.Assert.*;
import java.util.Calendar;
import org.junit.Test;
import org.mandarax.rt.ResultSet;
import test.org.mandarax.compiler.aggregation2.*;
/**
 * Test cases using generated code.
 * @author jens dietrich
 */
public class CompilerTestsAggregation2 {
	
//	rel Meeting(String venue,Calendar time) queries getAll(),getMeetings(venue) {
//		fact1: -> Meeting("NY",new GregorianCalendar(2000,1,2));
//		fact2: -> Meeting("NY",new GregorianCalendar(2002,2,3));
//		fact3: -> Meeting("NY",new GregorianCalendar(2001,3,4));
//	}
//
//	rel LastMeeting(String venue,Calendar time) queries getLastMeeting(venue) {
//		rule1:  -> LastMeeting(v,max t in Meeting(v,t));
//	}
//
//	rel FirstMeeting(String venue,Calendar time) queries getFirstMeeting(venue) {
//		rule1:  -> FirstMeeting(v,min t in Meeting(v,t));
//	}
	
	@Test
	public void test1() throws Exception {
		ResultSet<LastMeetingRel> rs = LastMeetingRelInstances.getLastMeeting("NY");
		assertTrue(rs.hasNext());
		assertEquals(2002,rs.next().time.get(Calendar.YEAR));
		assertFalse(rs.hasNext());
	}
	
	@Test
	public void test2() throws Exception {
		ResultSet<FirstMeetingRel> rs = FirstMeetingRelInstances.getFirstMeeting("NY");
		assertTrue(rs.hasNext());
		assertEquals(2000,rs.next().time.get(Calendar.YEAR));
		assertFalse(rs.hasNext());
	}
	
}
